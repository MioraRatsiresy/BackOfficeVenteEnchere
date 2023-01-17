use VenteEnchere

db.PhotoEnchere.insertOne({"_id":1,"idEnchere":1,"photo":"image/image1.jpg"})
db.PhotoEnchere.insertOne({"_id":2,"idEnchere":1,"photo":"image/image2.jpg"})
db.PhotoEnchere.insertOne({"_id":3,"idEnchere":2,"photo":"image/image3.jpg"})
db.PhotoEnchere.insertOne({"_id":4,"idEnchere":2,"photo":"image/image4.jpg"})

db.MiserEnchere.insertOne({"_id":1,"idclient":1,"idEnchere":1,"montant":200000,"dateheure":"2022-01-17 03:36"})
