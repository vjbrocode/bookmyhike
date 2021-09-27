Feature: all the trails can be retrieved

  Scenario: client makes call to fetch all trails
    When the client calls GET all trail URL /api/hike/v1/trails
    Then the client receives status code for fetch all trail 200
    And the client receives all trails json response "{\"_embedded\":{\"trailList\":[{\"id\":1,\"name\":\"Shire\",\"startAt\":\"07:00\",\"endAt\":\"09:00\",\"minimumAge\":5,\"maximumAge\":100,\"unitPrice\":29.9,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/api/hike/v1/trails/1\"},\"trails\":{\"href\":\"http://localhost:8080/api/hike/v1/trails\"}}},{\"id\":2,\"name\":\"Gondor\",\"startAt\":\"10:00\",\"endAt\":\"13:00\",\"minimumAge\":11,\"maximumAge\":50,\"unitPrice\":59.9,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/api/hike/v1/trails/2\"},\"trails\":{\"href\":\"http://localhost:8080/api/hike/v1/trails\"}}},{\"id\":3,\"name\":\"Mordor\",\"startAt\":\"14:00\",\"endAt\":\"19:00\",\"minimumAge\":18,\"maximumAge\":40,\"unitPrice\":99.9,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/api/hike/v1/trails/3\"},\"trails\":{\"href\":\"http://localhost:8080/api/hike/v1/trails\"}}}]},\"_links\":{\"self\":{\"href\":\"http://localhost:8080/api/hike/v1/trails\"}}}"

  Scenario: client makes call to fetch one trail by id
    When the client calls GET one trail URL /api/hike/v1/trails/1
    Then the client receives status code fetch one trail 200
    And the client receives one trail json response "{\"id\":1,\"name\":\"Shire\",\"startAt\":\"07:00\",\"endAt\":\"09:00\",\"minimumAge\":5,\"maximumAge\":100,\"unitPrice\":29.9,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/api/hike/v1/trails/1\"},\"trails\":{\"href\":\"http://localhost:8080/api/hike/v1/trails\"}}}"
