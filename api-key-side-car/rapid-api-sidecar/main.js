const http = require("http");
const url = require("url");
const axios = require("axios");
require("dotenv").config();

const port = process.env.PORT || 3000;
const proxyBaseUrl = process.env.PROXY_BASE_URL;
const rapidApiKey = process.env.RAPID_API_KEY;
const rapidApiHost = process.env.RAPID_API_HOST;

function onRequest(req, res) {
  const parts = url.parse(req.url, true);
  const query = parts.query;
  const path = parts.pathname;

  console.log(`proxying to ${proxyBaseUrl}/${path}`);

  const upstreamUrl = `${proxyBaseUrl}/${path.substring(1)}`;
  const options = {
    method: "GET",
    url: upstreamUrl,
    params: query,
    headers: {
      "X-RapidAPI-Key": rapidApiKey,
      "X-RapidAPI-Host": rapidApiHost,
    },
    responseType: "stream",
  };

  axios
    .request(options)
    .then((response) => {
      response.data.pipe(res);
    })
    .catch((error) => {
      error.response.data.pipe(res);
    });
}

http.createServer(onRequest).listen(port);
console.log(`Listening on port ${port}`);
