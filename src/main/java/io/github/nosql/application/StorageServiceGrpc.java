package io.github.nosql.application;

import com.google.protobuf.ByteString;
import io.github.nosql.InsertItem;
import io.github.nosql.Response;
import io.github.nosql.StorageServiceGrpc.StorageServiceImplBase;
import io.github.nosql.exception.KeyAlreadyExists;
import io.github.nosql.model.Data;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class StorageServiceGrpc extends StorageServiceImplBase {
    private static final Logger LOGGER = Logger.getLogger(StorageServiceGrpc.class.getSimpleName());
    private final StorageRepository storageRepository;

    public StorageServiceGrpc(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public void insertItem(InsertItem request, StreamObserver<Response> responseObserver) {
        try {
            Data data = new Data(request.getValue().toByteArray());
            storageRepository.persist(request.getKey(), data);
            responseObserver.onNext(Response.newBuilder()
                    .setKey(request.getKey())
                    .setId(data.getId())
                    .setValue(ByteString.copyFrom(data.getValue()))
                    .setTimestamp(data.getCreatedAt())
                    .build());
            responseObserver.onCompleted();
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, "Exception while persisting data by key", exception);
            if (exception instanceof KeyAlreadyExists) {
                responseObserver.onError(new StatusException(Status.ALREADY_EXISTS));
            } else {
                responseObserver.onError(new StatusException(Status.ABORTED));
            }
        }
    }
}
