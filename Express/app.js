const express = require("express")
const csvParser = require("csv-parser");

const app = express()

app.get("/", (req, res) => {
  res.send("Hello World")
})

app.listen(3000)
