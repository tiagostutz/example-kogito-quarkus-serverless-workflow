const http = require("http");
const url = require("url");
const axios = require("axios");
require("dotenv").config();

const port = process.env.PORT || 3000;
const proxyBaseUrl = process.env.PROXY_BASE_URL;
const rapidApiKey = process.env.RAPID_API_KEY;
const rapidApiHost = process.env.RAPID_API_HOST;
const mockFilePath = process.env.MOCK_FILE_PATH;

function onRequest(req, res) {
  const parts = url.parse(req.url, true);
  const query = parts.query;
  const path = parts.pathname;

  const upstreamUrl = `${proxyBaseUrl}/${path.substring(1)}`;
  console.log(
    `proxying to ${upstreamUrl} with params ${JSON.stringify(query)}`
  );

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

  if (process.env.NODE_ENV === "development" && mockFilePath) {
    res.writeHead(200, { "Content-Type": "application/json" });
    const mockCostOfLiving = require(`./${mockFilePath}`);
    return res.end(JSON.stringify(mockCostOfLiving));
  }

  axios
    .request(options)
    .then((response) => {
      res.writeHead(200, response.headers);
      response.data.pipe(res);
    })
    .catch((error) => {
      console.error(error);
      error.response.data.pipe(res);
    });
}

http.createServer(onRequest).listen(port);
console.log(`Listening on port ${port}`);
