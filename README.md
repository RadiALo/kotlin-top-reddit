# Top Reddit app
This is a simple client app that allows you to view the top posts from Reddit.
## Functionality
The created application provides the ability to:
- View top posts from Reddit.
- View attached images.
- Upload images to the gallery.
## Structure
The structure of the project includes:
- Post model that includes information about an individual post from Reddit.
- The PostService service and its RedditPostService implementation. This service provides methods for receiving site posts by pages.
The default pagination is 25 elements. The service has the following methods:
  - loadFirstPage() - returns first page as a list of posts
  - loadNextPage() - returns next page as a list of posts
  - loadPrevPage() - returns previous page as a list of posts
  - reloadPage() - returns current page as a list of posts
- PostMapper is a class that converts JSONObject and JSONArray objects to Post model objects.
- Also, a PostAdapter class was created to display the list of posts on the RecyclerView.
  - The PostScrollListener class works together with the adapter, uploading new posts as needed.
## Activity
The project includes three activities:
- activity_main which displays a list of posts.
- item_post which displays a single post.
- image_upscale which displays upscaled post image.
