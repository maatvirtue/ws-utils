package net.nlacombe.wsutils.restexception.api;

public class UnknownRestException extends RestException
{
	public UnknownRestException(int httpStatus, String code, String message)
	{
		super(httpStatus, code, message);
	}

	@Override
	public String getMessage()
	{
		return "Unknown rest exception code \"" + getErrorCode() + "\" with message \"" + super.getMessage() + "\"";
	}
}
