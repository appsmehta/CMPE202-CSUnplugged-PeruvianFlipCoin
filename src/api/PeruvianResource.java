package api ;

import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.* ;
import pfc.Driver ;

public class PeruvianResource extends ServerResource
{
	Driver driver = Driver.getInstance();

	@Post
	public Representation EncodeInputs(JsonRepresentation input)
	{
		JSONObject json = input.getJsonObject();
		String inputFunction = json.getString("function");

		if(inputFunction.equals("captainA"))
		{
			String encodedInputBits = json.getString("inputBits");
			String output = driver.encodedInput(encodedInputBits);

			JSONObject response = new JSONObject() ;
        	//String state = machine.getStateString() ;
        	response.put( "result", output ) ;

        	return new JsonRepresentation ( response ) ;
		}



        if(inputFunction.equals("captainB"))
        {
       		int encodedInputParity = Integer.parseInt(json.getString("inputParity"));
			String output = driver.guessParity(encodedInputParity);

			JSONObject response = new JSONObject() ;
        	//String state = machine.getStateString() ;
        	response.put( "result", output ) ;

        	return new JsonRepresentation ( response ) ;
        }

        	JSONObject response = new JSONObject() ;
        	//String state = machine.getStateString() ;
        	response.put( "result", "ERROR" ) ;

        	return new JsonRepresentation ( response ) ;
	} 
}