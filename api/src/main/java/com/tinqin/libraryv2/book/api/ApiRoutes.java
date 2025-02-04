package com.tinqin.libraryv2.book.api;

public class ApiRoutes {

  public static final String API = "/api/v2";

  public static final String API_BOOKS = API + "/books";
  public static final String API_BOOKS_ID = API_BOOKS + "/{bookId}";
  public static final String API_BOOKS_OPEN_LIB = API_BOOKS_ID + "/open-lib-first";
  public static final String API_BOOKS_OPEN_LIB_ALL = API_BOOKS_ID + "/open-lib";
  public static final String API_BOOKS_QUERY_OPEN_LIB = API + "/open-lib";
  public static final String API_BOOKS_QUERY_ALGO_LIB = API + "/algo-lib";

  public static final String API_AUTHORS= API + "/authors";
  public static final String API_AUTHORS_ID= API_AUTHORS +  "/{authorId}";
  public static final String API_AUTHORS_OPEN_LIB= API_AUTHORS_ID +  "/open-lib-list";
  public static final String API_AUTHORS_OPEN_LIB_ADD= API_AUTHORS_ID +  "/open-lib-add";
  public static final String API_AUTHORS_ALGO_LIB= API_AUTHORS_ID +  "/algo-lib-list";
  public static final String API_AUTHORS_GOOGLE= API_AUTHORS_ID +  "/google-books-list";
  public static final String API_AUTHORS_GOOGLE_ADD= API_AUTHORS_ID +  "/google-books-add";
}
