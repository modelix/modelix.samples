# rest-api-model-ql

This project provides a REST API that wraps the functionality of the `light-model-server` MPS plugin running inside an MPS instance.
The provided REST API conforms to the API spec in the [openapi.yaml](../actual-rest-api/openapi.yaml).

## Building

You can build this subproject individually from the parent folder via all gradle:
```
./gradlew rest-api-model-ql:build
```

Before running the API provider you need to open the MPS project in the [mps](../mps) folder. 
The initial gradle build will automatically have set up MPS with the [`light-model-server` plugin](https://github.com/modelix/modelix/tree/mps/2020.3/mps) and expose the models via query language called ModelQL.
This API is then accessed from this openAPI abstraction implementation to load the models.

Once your MPS project is opened you can run:

```
./gradlew rest-api-model-ql:run
```

Once you see a log message like:
```
[DefaultDispatcher-worker-8] INFO  ktor.application - Responding at http://0.0.0.0:8090
```

Your openAPI implementation is ready.



Settings such as ports and hosts are defined in the [application.conf](src/main/resources/application.conf).


## Usage

For simple testing you can `curl` the data provided by the API for example:
<details>
<summary>
Unfold for examples
</summary>

```console
$ curl -s -X GET "http://localhost:8090/rooms" -H  "accept: application/json" | jq
{
  "rooms": [
    {
      "roomRef": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058347",
      "name": "somehjtinghekjrekjrhe",
      "maxPlaces": 32232121,
      "hasRemoteEquipment": false
    },
    {
      "roomRef": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058349",
      "name": "Schrödinger",
      "maxPlaces": 420,
      "hasRemoteEquipment": true
    }
  ]
}


$ curl -s -X GET "http://localhost:8090/rooms/mps-node%253Ar%253Ace161c54-ea76-40a6-a31d-9d7cd01febe2%2528University.Schedule.sandbox%2529%252F4128798754188058349" -H  "accept: application/json" | jq
{
   "roomRef": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058349",
   "name": "Schrödinger",
   "maxPlaces": 420,
   "hasRemoteEquipment": true
}

$ curl -s -X GET "http://localhost:8090/rooms/mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058347" -H  "accept: application/json" | jq
{
  "roomRef": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058347",
  "name": "somehjtinghekjrekjrhe",
  "maxPlaces": 32232121,
  "hasRemoteEquipment": false
}


$ curl -s -X GET "http://localhost:8090/rooms/trash" -H  "accept: application/json" | jq
"Can not load Room: null

$ curl -s -X GET "http://localhost:8090/lectures" -H  "accept: application/json" | jq
curl -s -X GET "http://localhost:8090/lectures" -H  "accept: application/json" | jq
{
  "lectures": [
    {
      "lectureRef": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058353",
      "name": "Physics 101",
      "description": "You learn about stuff",
      "maxParticipants": 42,
      "room": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058347"
    },
    {
      "lectureRef": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F1305729863392535677",
      "name": "Physics 102",
      "description": "You learn about stuff",
      "maxParticipants": 42,
      "room": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058349"
    },
    {
      "lectureRef": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188060854",
      "name": "New Students Welcome",
      "description": "Hello everyone",
      "maxParticipants": 69,
      "room": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058349"
    }
  ]
}

$ curl -s -X GET "http://localhost:8090/lectures/mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058353" -H  "accept: application/json" | jq
{
  "lectureRef": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058353",
  "name": "Physics 101",
  "description": "You learn about stuff",
  "maxParticipants": 42,
  "room": "mps-node%3Ar%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2%28University.Schedule.sandbox%29%2F4128798754188058347"
}

$ curl -s -X GET "http://localhost:8090/lectures/trash" -H  "accept: application/json" | jq
"Can not load Lecture: null"

```
</details>


Alternatively you can now start the [dashboard](../spa-dashboard-angular) to consume this API endpoint.