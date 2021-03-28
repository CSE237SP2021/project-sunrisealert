package SunriseAlert;

public class SunriseAlert {
    public static void main(String args[]) {
        System.out.println("Hello world");
        NewsApiClient newsApiClient = new NewsApiClient("2a7f891e21c64fe59f2114971a77e92d");
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                .q("bitcoin")
                .language("en")
                .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println(response.getArticles().get(0).getTitle());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
                );
    }
}
