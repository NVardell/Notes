var obj = {id : "007", name : "James Bond"};
console.log(obj);                    // Object { id: "007", name: "James Bond" }
console.log(JSON.stringify(obj));    //{"id":"007","name":"James Bond"}
if (obj.hasOwnProperty("id")){
    console.log(obj.id);             //007
}