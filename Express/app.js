const express = require("express")
const csvParser = require("csv-parser");

const app = express()

const result = [];

fs.createReadStream("./data.csv")
  .pipe(csvParser())
  .on("data", (data) => {
    result.push(data);
  })
  .on("end", () => {
    console.log(result);
  });


app.get("/", (req, res) => {
  res.send("Hello World")
})

app.listen(3000)
