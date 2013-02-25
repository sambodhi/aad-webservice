package com.aad.ws.dao;

import java.util.List;
import com.aad.ws.domain.Results;
import com.aad.ws.exception.InvalidAttribute;

public interface ResultDAO {
	Results submitResult(Results res) throws InvalidAttribute;
}

