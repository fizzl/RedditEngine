RedditEngine

This is a Java libray, which uses some Android features.

Usage:

1. Add the library to you build path
2. Instantiate RedditApi like this:
   RedditApi api = DefaultRedditApi.getInstance();
3. Whenever doing operations, make sure the library is aware of the context 
   you are in: 
   api.setContext(currentContext);
4. See interface RedditApi for functions you can call. See 
   http://www.reddit.com/dev/api for details on each end point.
   
The library is pretty well commented. You may run JavaDoc on the project to get
full API doc.
 
