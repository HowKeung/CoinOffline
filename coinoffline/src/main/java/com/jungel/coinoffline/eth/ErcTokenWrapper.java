package com.jungel.coinoffline.eth;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import rx.Observable;
import rx.functions.Func1;

public class ErcTokenWrapper extends Contract {

    private static final String BINARY = "contract binary key";

    private ErcTokenWrapper(String contractAddress, Web3j web3j, Credentials credentials,
                            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private ErcTokenWrapper(String contractAddress, Web3j web3j, TransactionManager
            transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Transfer",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }, new TypeReference<Address>() {
                }),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>
                (valueList.size());
        for (EventValues eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter
                                                                             startBlock,
                                                                     DefaultBlockParameter
                                                                             endBlock) {
        final Event event = new Event("Transfer",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }, new TypeReference<Address>() {
                }),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._transactionHash = log.getTransactionHash();
                return typedResponse;
            }
        });
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Approval",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }, new TypeReference<Address>() {
                }),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>
                (valueList.size());
        for (EventValues eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse._owner = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._spender = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApprovalEventResponse> approvalEventObservable(DefaultBlockParameter
                                                                             startBlock,
                                                                     DefaultBlockParameter
                                                                             endBlock) {
        final Event event = new Event("Approval",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }, new TypeReference<Address>() {
                }),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse._owner = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._spender = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._transactionHash = log.getTransactionHash();
                return typedResponse;
            }
        });
    }

    public Utf8String name() throws IOException {
        Function function = new Function("name",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }));
        return executeCallSingleValueReturn(function);
    }

    public TransactionReceipt approve(Address _spender, Uint256 _amount) throws IOException,
            TransactionException {
        Function function = new Function("approve", Arrays.<Type>asList(_spender, _amount),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public Future<Uint256> totalSupply() throws IOException {
        Function function = new Function("totalSupply",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeCallSingleValueReturn(function);
    }

    public TransactionReceipt transferFrom(Address _from, Address _to, Uint256 _amount) throws
            IOException, TransactionException {
        Function function = new Function("transferFrom", Arrays.<Type>asList(_from, _to, _amount)
                , Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public Uint8 decimals() throws IOException {
        Function function = new Function("decimals",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
                }));
        return executeCallSingleValueReturn(function);
    }

    public Uint256 balanceOf(Address _owner) throws IOException {
        Function function = new Function("balanceOf",
                Arrays.<Type>asList(_owner),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeCallSingleValueReturn(function);
    }

    public Future<Address> owner() throws IOException {
        Function function = new Function("owner",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeCallSingleValueReturn(function);
    }

    public Utf8String symbol() throws IOException {
        Function function = new Function("symbol",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }));
        return executeCallSingleValueReturn(function);
    }

    public TransactionReceipt transfer(Address _to, Uint256 _amount) throws IOException,
            TransactionException {
        Function function = new Function("transfer", Arrays.<Type>asList(_to, _amount),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public Future<Uint256> allowance(Address _owner, Address _spender) throws IOException {
        Function function = new Function("allowance",
                Arrays.<Type>asList(_owner, _spender),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeCallSingleValueReturn(function);
    }

    public static RemoteCall<ErcTokenWrapper> deploy(Web3j web3j, Credentials credentials,
                                                     BigInteger gasPrice, BigInteger gasLimit,
                                                     BigInteger initialWeiValue, Uint256
                                                               totalSupply, Utf8String tokenName,
                                                     Uint8 decimalUnits, Utf8String tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList
                (totalSupply, tokenName, decimalUnits, tokenSymbol));
        return deployRemoteCall(ErcTokenWrapper.class, web3j, credentials, gasPrice, gasLimit,
                BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<ErcTokenWrapper> deploy(Web3j web3j, TransactionManager
            transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger
            initialWeiValue, Uint256 totalSupply, Utf8String tokenName, Uint8 decimalUnits,
                                                     Utf8String tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList
                (totalSupply, tokenName, decimalUnits, tokenSymbol));
        return deployRemoteCall(ErcTokenWrapper.class, web3j, transactionManager, gasPrice,
                gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static ErcTokenWrapper load(String contractAddress, Web3j web3j, Credentials
            credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ErcTokenWrapper(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ErcTokenWrapper load(String contractAddress, Web3j web3j, TransactionManager
            transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ErcTokenWrapper(contractAddress, web3j, transactionManager, gasPrice,
                gasLimit);
    }

    public static class TransferEventResponse {
        public Address _from;

        public Address _to;

        public Uint256 _value;

        public String _transactionHash;
    }

    public static class ApprovalEventResponse {
        public Address _owner;

        public Address _spender;

        public Uint256 _value;

        public String _transactionHash;
    }
}
