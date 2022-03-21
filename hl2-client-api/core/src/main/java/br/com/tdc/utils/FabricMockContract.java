package br.com.tdc.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractEvent;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Transaction;
import org.hyperledger.fabric.gateway.spi.Checkpointer;

public class FabricMockContract implements Contract {

	@Override
	public Transaction createTransaction(String name) {
		return null;
	}

	@Override
	public byte[] submitTransaction(String name, String... args)
			throws ContractException, TimeoutException, InterruptedException {
		return null;
	}

	@Override
	public byte[] evaluateTransaction(String name, String... args) throws ContractException {
		return null;
	}

	@Override
	public Consumer<ContractEvent> addContractListener(Consumer<ContractEvent> listener) {
		return null;
	}

	@Override
	public Consumer<ContractEvent> addContractListener(Consumer<ContractEvent> listener, String eventName) {
		return null;
	}

	@Override
	public Consumer<ContractEvent> addContractListener(Consumer<ContractEvent> listener, Pattern eventNamePattern) {
		return null;
	}

	@Override
	public Consumer<ContractEvent> addContractListener(Checkpointer checkpointer, Consumer<ContractEvent> listener)
			throws IOException {
		return null;
	}

	@Override
	public Consumer<ContractEvent> addContractListener(Checkpointer checkpointer, Consumer<ContractEvent> listener,
			String eventName) throws IOException {
		return null;
	}

	@Override
	public Consumer<ContractEvent> addContractListener(Checkpointer checkpointer, Consumer<ContractEvent> listener,
			Pattern eventNamePattern) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consumer<ContractEvent> addContractListener(long startBlock, Consumer<ContractEvent> listener) {
		return null;
	}

	@Override
	public Consumer<ContractEvent> addContractListener(long startBlock, Consumer<ContractEvent> listener,
			String eventName) {
		return null;
	}

	@Override
	public Consumer<ContractEvent> addContractListener(long startBlock, Consumer<ContractEvent> listener,
			Pattern eventNamePattern) {
		return null;
	}

	@Override
	public void removeContractListener(Consumer<ContractEvent> listener) {
	}

}
