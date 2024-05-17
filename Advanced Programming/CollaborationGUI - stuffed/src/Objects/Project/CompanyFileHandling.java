package Objects.Project;

import Exceptions.ABNLengthIncorrectException;
import Exceptions.ABNValuesIncorrectException;
import Exceptions.IDOutOfRangeException;
import Objects.FileHandling;

public class CompanyFileHandling extends Company implements FileHandling{
	private static final long serialVersionUID = 1160245697924162230L;

	public CompanyFileHandling(String id, String name, String ABN, String URL, String address) throws ABNValuesIncorrectException, ABNLengthIncorrectException, IDOutOfRangeException {
		super(id, name, ABN, URL, address);
	}

	@Override
	public String toString() {
		return getID() + ";" + getName() + ";" + getABN() + ";" + getURL() + ";" + getAddress();
	}

}
