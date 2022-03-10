void sendMessage(
    String url,
    String channel,
    String username,
    String text,
    String icon='https://www.mattermost.org/wp-content/uploads/2016/04/icon.png'
  ) {
    httpRequest contentType: 'APPLICATION_JSON_UTF8',
        httpMode: 'POST',
        requestBody: "{ \
            \"channel\": \"${channel}\", \
            \"username\": \"${username}\", \
            \"icon_url\": \"${icon}\", \
            \"text\": \"${text}\" \
        }",
        responseHandle: 'NONE',
        url: "${url}",
        wrapAsMultipart: false
}

return this
