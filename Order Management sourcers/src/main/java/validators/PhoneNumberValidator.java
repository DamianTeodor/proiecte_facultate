package validators;

import model.Client;

public class PhoneNumberValidator implements Validator<Client>
{

    @Override
    public void validate(Client client)
    {
        if (client.getPhone_Number().length() < 10 || client.getPhone_Number().length() > 11)
        {
            throw new IllegalArgumentException("Not a valid phone number!");
        }
    }
}
