const fs = require("fs");
const express = require("express");
const csvParser = require("csv-parser");

const app = express()

const result = [];

fs.createReadStream("data.csv")
  .pipe(csvParser())
  .on("data", (data) => {
    result.push(data);
  })
  .on("end", () => {
    console.log(result);
  });
fs.writeFile("data2.csv", result, "utf-8", (err) => {
  if (err) console.log(err);
  else console.log("Data saved");
});

app.get("/", (req, res) => {
  res.send("Hello World")
})

app.listen(3000)
