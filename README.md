API Endpoints via postman.

1. Store Employee Details

POST /api/employees/save

Request Body:
{
    "id": 1,
    "firstName": "Srikanth",
    "lastName": "Muddu",
    "email": "srikanth.muddu57@outlook.com",
    "phoneNumbers": ["999999999", "0987654321"],
    "doj": "2023-05-16",
    "salary": 50000
}

2. Get Employee Tax Details

GET /api/employees/{id}/taxDetail   ->  GET /api/employees/1/taxDetail

3. Get All Employee Details

GET /api/employees/getAll

This endpoint returns the tax deduction details for an employee for the current financial year (April to March).

curl -X GET http://localhost:8090/api/employees/1/taxDetail
