package alex.silantev.dronzilla.constraints;

import alex.silantev.dronzilla.exceptions.BizServiceException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class BizConstraintProcessor {

    private final BizConstraint[] constraints;

    public static BizConstraintProcessor of(BizConstraint... constraints) {
        return new BizConstraintProcessor(constraints);
    }

    public void check() throws BizServiceException {
        Arrays.stream(constraints).forEach(BizConstraint::check);
    }
}
