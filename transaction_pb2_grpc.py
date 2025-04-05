# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc
import warnings

import transaction_pb2 as transaction__pb2

GRPC_GENERATED_VERSION = '1.71.0'
GRPC_VERSION = grpc.__version__
_version_not_supported = False

try:
    from grpc._utilities import first_version_is_lower
    _version_not_supported = first_version_is_lower(GRPC_VERSION, GRPC_GENERATED_VERSION)
except ImportError:
    _version_not_supported = True

if _version_not_supported:
    raise RuntimeError(
        f'The grpc package installed is at version {GRPC_VERSION},'
        + f' but the generated code in transaction_pb2_grpc.py depends on'
        + f' grpcio>={GRPC_GENERATED_VERSION}.'
        + f' Please upgrade your grpc module to grpcio>={GRPC_GENERATED_VERSION}'
        + f' or downgrade your generated code using grpcio-tools<={GRPC_VERSION}.'
    )


class WalletServiceStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.Transfer = channel.stream_stream(
                '/wallet.WalletService/Transfer',
                request_serializer=transaction__pb2.TransferRequest.SerializeToString,
                response_deserializer=transaction__pb2.TransferResponse.FromString,
                _registered_method=True)
        self.Transfers = channel.unary_unary(
                '/wallet.WalletService/Transfers',
                request_serializer=transaction__pb2.TransferRequests.SerializeToString,
                response_deserializer=transaction__pb2.TransferResponses.FromString,
                _registered_method=True)
        self.GetWallet = channel.unary_unary(
                '/wallet.WalletService/GetWallet',
                request_serializer=transaction__pb2.WalletRequest.SerializeToString,
                response_deserializer=transaction__pb2.WalletResponse.FromString,
                _registered_method=True)


class WalletServiceServicer(object):
    """Missing associated documentation comment in .proto file."""

    def Transfer(self, request_iterator, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def Transfers(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def GetWallet(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_WalletServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'Transfer': grpc.stream_stream_rpc_method_handler(
                    servicer.Transfer,
                    request_deserializer=transaction__pb2.TransferRequest.FromString,
                    response_serializer=transaction__pb2.TransferResponse.SerializeToString,
            ),
            'Transfers': grpc.unary_unary_rpc_method_handler(
                    servicer.Transfers,
                    request_deserializer=transaction__pb2.TransferRequests.FromString,
                    response_serializer=transaction__pb2.TransferResponses.SerializeToString,
            ),
            'GetWallet': grpc.unary_unary_rpc_method_handler(
                    servicer.GetWallet,
                    request_deserializer=transaction__pb2.WalletRequest.FromString,
                    response_serializer=transaction__pb2.WalletResponse.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'wallet.WalletService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))
    server.add_registered_method_handlers('wallet.WalletService', rpc_method_handlers)


 # This class is part of an EXPERIMENTAL API.
class WalletService(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def Transfer(request_iterator,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.stream_stream(
            request_iterator,
            target,
            '/wallet.WalletService/Transfer',
            transaction__pb2.TransferRequest.SerializeToString,
            transaction__pb2.TransferResponse.FromString,
            options,
            channel_credentials,
            insecure,
            call_credentials,
            compression,
            wait_for_ready,
            timeout,
            metadata,
            _registered_method=True)

    @staticmethod
    def Transfers(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(
            request,
            target,
            '/wallet.WalletService/Transfers',
            transaction__pb2.TransferRequests.SerializeToString,
            transaction__pb2.TransferResponses.FromString,
            options,
            channel_credentials,
            insecure,
            call_credentials,
            compression,
            wait_for_ready,
            timeout,
            metadata,
            _registered_method=True)

    @staticmethod
    def GetWallet(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(
            request,
            target,
            '/wallet.WalletService/GetWallet',
            transaction__pb2.WalletRequest.SerializeToString,
            transaction__pb2.WalletResponse.FromString,
            options,
            channel_credentials,
            insecure,
            call_credentials,
            compression,
            wait_for_ready,
            timeout,
            metadata,
            _registered_method=True)
