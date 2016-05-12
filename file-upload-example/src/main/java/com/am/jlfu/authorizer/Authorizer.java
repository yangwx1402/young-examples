package com.am.jlfu.authorizer;


import com.am.jlfu.fileuploader.exception.AuthorizationException;
import com.am.jlfu.fileuploader.web.UploadServletAction;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;



/**
 * Allows or not a user to perform an operation on the JLFU api.
 * 
 * @author antoinem
 * 
 */
public interface Authorizer {

	/**
	 * @param request
	 *            the initial servlet request
	 * @param action
	 *            the action that the client wishes to perform
	 * @param clientId
	 *            the identifier of the client
	 * @param optionalFileIds
	 *            if available, the file id(s)
	 * @throws AuthorizationException
	 *             if the client cannot perform the action on this file
	 */
	void getAuthorization(HttpServletRequest request, UploadServletAction action, UUID clientId, UUID... optionalFileIds)
			throws AuthorizationException;

}
