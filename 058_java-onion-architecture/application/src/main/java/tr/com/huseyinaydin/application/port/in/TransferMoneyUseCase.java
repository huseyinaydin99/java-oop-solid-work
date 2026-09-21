package tr.com.huseyinaydin.application.port.in;

import tr.com.huseyinaydin.application.dto.TransferRequest;

public interface TransferMoneyUseCase {
    void transfer(TransferRequest request);
}
