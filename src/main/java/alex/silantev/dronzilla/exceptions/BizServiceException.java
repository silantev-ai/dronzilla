package alex.silantev.dronzilla.exceptions;

import alex.silantev.dronzilla.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BizServiceException extends RuntimeException {

    private final ErrorCode errorCode;
}
