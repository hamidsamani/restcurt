[![Build Status](https://travis-ci.org/hamidsamani/restcurt.svg?branch=master)](https://travis-ci.org/hamidsamani/restcurt)
 
## RESTCurt Framework
The RESTCurt framework provides a simple, fast, minimalist RESTful web framwork for Java.

###Quick Guide

Declare your handlers by extending `AbstractRouteHandler` and implementing any of predefined methods, for example:

```java

	public class PersonResource extends AbstractRouteHandler {

		//It could be any DAO.
		private PersonRepository repository = new PersonRepository();


		@Override
		public void route(RouteBuilder routes) {
			routes
	
					//Matches GET requests to /persons .
					.get("/persons", (req, res) -> res.toJson()
													  .status(HttpStatus.OK)
													  .body(repository.findAll()))
	
					//Matches GET requests to /persons/:id , :id will be match 
					//if it matches with \\d+ regex pattern.
					.get("/persons/:id(\\d+)", (req, res) -> res.toJson()
																.body(repository.findOne(req.variable("id"))))
	
					//Matches PUT requests to /persons/:id , :id will be match if it matches with \\d+ regex pattern.
					.put("/persons/:id(\\d+)", (req, res) -> res.toJson()
																.body(repository.update(req.variable("id"), req.param("name")))
																.status(HttpStatus.OK))
	
					//Matches POST requests to /persons /
					.post("/persons", (req, res) -> res.toJson()
														.status(HttpStatus.CREATED)
														.body(repository.save(req.param("id"), req.param("name"))))
	
					//Matches DELETE requests to /persons/:id , :id will be match if it matches with \\d+ regex pattern.
					.delete("/persons/:id(\\d+)", (req, res) -> {
						repository.delete(req.variable("id"));
						res.toJson().status(HttpStatus.OK);
					});
		}

		@Override
		public void exception(ExceptionHandlerBuilder exceptions) {
			exceptions
					//In the case of throwing PersonNotFoundException in any of handlers, this handler will be invoke.
					.exception(PersonNotFoundException.class, (req, res) -> res.toJson()
																				.status(HttpStatus.NOT_FOUND)
																				.body("person not found!"));
		}
	}
```
Now register your route handler as follow : 

```java

	public class RestCurtApplication {
    	public static void main(String[] args) {

        	//just declare your resources as run method argument to RESTCurt creates your mappings.
        	RestCurt.run(PersonResource.class);
    	}
	}
```
Then simply run main method of this class.