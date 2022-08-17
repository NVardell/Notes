const csv = require('csv-parser');  // https://github.com/mafintosh/csv-parser
const fs = require('fs');
const results = [];

fs.createReadStream('data.csv')
  .pipe(csv(["Name", "Phone"]))
  .on('data', (data) => results.push(data))
  .on('end', () => {
    console.log(results);
  });
// fs.writeFile("data2.csv", results, "utf-8", (err) => {
//   if (err) console.log(err);
//   else console.log("Data saved");
// });
