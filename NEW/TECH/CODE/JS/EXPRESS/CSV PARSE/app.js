const fs = require('fs');
const fastcsv = require('fast-csv');
const tiny = require('tinygen');
const csv = require('@fast-csv/parse');

const csvFileName = 'data.csv';
const ws = fs.createWriteStream("out.csv");
let rows = [];

csv.parseFile(csvFileName)
    .on('error', error => console.error(error))
    .on('data', row => {rows.push(row); console.log(`ROW=${JSON.stringify(row)}`); console.log("Test. Rows = " + rows)})
    .on('end', rowCount => {
      console.log(`\nParsed ${rowCount} rows:`);
      fastcsv
        .write(rows, { headers: true })
        .pipe(ws)
    })
    .on('finish', () => console.log('Done writing csv file.'));
