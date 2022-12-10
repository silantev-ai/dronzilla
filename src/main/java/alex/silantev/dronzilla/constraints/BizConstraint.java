package alex.silantev.dronzilla.constraints;

import alex.silantev.dronzilla.exceptions.BizServiceException;

public interface BizConstraint {

    void check() throws BizServiceException;
}
