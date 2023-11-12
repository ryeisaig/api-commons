# api-commons
This java library contains the common classes for API Development such as BadRequestException, BaseResponse, Messages, ResultCodes and ExceptionAdvice


**ExceptionAdvice.java**

This class handles the runtime exceptions thrown and converts them to error response eg. Bad Request

Currently, it supports the ff. exceptions

- **BadRequestException** - This is a custom exception to tell that client sent an invalid request.
- **MethodArgumentNotValidException** - This is thrown when an input in the request fails a validation and the server would return the list of field errors
- **RuntimeException** - Generic exceptions for all runtime errors
- **Exception** - Generic exception for all other errors that the server could not handle

Example response when a MethodArgumentNotValidException is thrown

```
400
{
   "code": "1001",
   "title": "Error",
   "message": "Please check your inputs.",
   "content": null,
   "errors": {
	"page": "must be a positive integer"
   }
}
```