import grpc
import pytest
import transaction_pb2
import transaction_pb2_grpc
import time

@pytest.fixture
def grpc_stub():
    # Connect to the gRPC server at 18.162.252.199:50052
    channel = grpc.insecure_channel('18.162.252.199:50052')
    stub = transaction_pb2_grpc.WalletServiceStub(channel)
    yield stub
    channel.close()

def test_get_wallet(grpc_stub):
    # Record the start time
    start_time = time.time()
    print(f"Test start time: {time.ctime(start_time)}")

    # Create a list of user_ids from 21185 to 71184 (total 50,000 user_ids)
    user_ids = range(70000, 71185)

    # Variable to track if all checks pass
    all_checks_passed = True

    # Iterate through each user_id and send a request
    for user_id in user_ids:
        # Print the request number to confirm progress
        print(f"Checking user_id={user_id}, total requests sent: {user_id - 70000 + 1}")

        # Create a username corresponding to the user_id (e.g., "user21185", "user21186", ...)
        username = f"user{user_id}"

        # Create a GetWallet request for each user_id with the corresponding username
        request = transaction_pb2.WalletRequest(
            agency_id=1,           # "agency_id": "1"
            username=username,     # Username changes based on user_id
            user_id=user_id,       # user_id ranges from 21185 to 71184
            main_balance=0,        # Default value
            time=0,                # Default value
            wallet_type="99"       # "wallet_type": "99"
        )

        # Send the request and handle any errors
        response = None
        try:
            response = grpc_stub.GetWallet(request)
            # Print the response immediately after receiving it
            print(f"Response from gRPC server for user_id={user_id}, username={username}:")
            print(response)
        except grpc.RpcError as e:
            print(f"Failed to connect to gRPC server for user_id={user_id}: {e.code()} - {e.details()}")
            all_checks_passed = False
            continue

        # Perform flexible checks on the response fields
        try:
            assert response.username == username, f"Username mismatch for user_id={user_id}"
            assert response.wallet_type == 0, f"wallet_type incorrect for user_id={user_id}"
            assert response.status == transaction_pb2.APIStatus.Value("OK"), f"Status is not OK for user_id={user_id}"
            assert response.error_code == 0, f"error_code incorrect for user_id={user_id}"
            assert response.message == "", f"Message is not empty for user_id={user_id}"

            assert len(response.wallets) == 1, f"Wallets list does not have 1 element for user_id={user_id}"
            wallet = response.wallets[0]
            assert wallet.user_id == user_id, f"wallets[0].user_id incorrect for user_id={user_id}"
            assert wallet.type == 99, f"wallets[0].type incorrect for user_id={user_id}"
            assert wallet.member_id == 0, f"wallets[0].member_id incorrect for user_id={user_id}"

            assert isinstance(response.extra_balance, float) and response.extra_balance >= 0, f"extra_balance invalid for user_id={user_id}"
            assert isinstance(response.main_balance, float) and response.main_balance >= 0, f"main_balance invalid for user_id={user_id}"
            assert isinstance(response.time, int) and response.time > 0, f"time invalid for user_id={user_id}"
            assert isinstance(wallet.balance, float) and wallet.balance >= 0, f"wallets[0].balance invalid for user_id={user_id}"
            if wallet.time:
                assert isinstance(wallet.time, int) and wallet.time > 0, f"wallets[0].time invalid for user_id={user_id}"

            print(f"All checks passed for user_id={user_id}, username={username}!")
        except AssertionError as e:
            print(f"Error in checks for user_id={user_id}: {str(e)}")
            all_checks_passed = False

    # Record the end time
    end_time = time.time()
    print(f"Test end time: {time.ctime(end_time)}")

    # Calculate and print the execution time
    run_time = end_time - start_time
    print(f"Script execution time: {run_time:.2f} seconds")

    # If any check fails, raise an error to make pytest report FAILED
    if not all_checks_passed:
        pytest.fail(f"One or more checks failed for user_ids from 21185 to 71184 (total execution time: {run_time:.2f} seconds)")
