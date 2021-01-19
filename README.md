# QSA Calculator ![CircleCI](https://img.shields.io/circleci/build/github/Queueing-Systems-Assistance/qsa-calculator/master)

### Project description

This service is responsible for calculating system features and retrieve its properties. To use the service, please provide the following values in the `build.gradle` file:
```groovy
  githubToken = 'YOUR_TOKEN'
  githubUsername = 'YOUR_USERNAME'
```

### Endpoints

- For tracking set the `X-Request-Id` header value
- For different locales, set the `Accept-Language` header value (en_US, hu_HU, etc.)


#### /system

- Accepts only POST requests
- Need a JSON body (example):
```json
["systemMM1"]
```
- Response (example):
```json
[
    {
        "name": "M | M | 1",
        "id": "systemMM1",
        "status": "OK",
        "description": "Poisson process<br>Exponentially distributed arrival times<br>Exponentially distributed service times<br><b>1</b> server",
        "inputs": [
            {
                "id": "Lambda",
                "name": "λ",
                "description": "Summarized arrival rate",
                "typeFraction": true,
                "required": true
            }
        ]
    }
]
```

#### /system/{systemId}/outputs

- Accepts only POST requests
- Replace `{systemId}` with a correct systemId (eg. systemMM1)
- Need a JSON body (example):
```json
{
    "featureConditions": {
        "Lambda": 1,
        "Mu": 4,
        "n": 1,
        "r": 1,
        "t": 0.8
    },
    "outputFeatureIds": [
      "Ro"
    ] 
}
```
- Response (example):
```json
[
    {
        "id": "Ro",
        "name": "ρ",
        "description": "Traffic intensity, unit of measure is the erlang",
        "values": [
            "0.25"
        ]
    }
]
```

#### /system/{systemId}/outputs/stream

- Same as the `/system/{systemId}/outputs`
- Need a JSON body (example):
```json
{
    "featureConditions": {
        "Lambda": 4,
        "Mu": 6,
        "n": 1,
        "r": 6,
        "t": 7
    },
    "streamOutput": {
        "from": 4,
        "to": 1,
        "steps": 0.5,
        "featureId": "Lambda"
    },
    "outputFeatureIds": [
        "Ro"
    ]
}
```
- Response (example):
```json
{
    "outputFeatures": [
        {
            "id": "Ro",
            "name": "ρ",
            "description": "Traffic intensity, unit of measure is the erlang",
            "values": [
                "0.6666666666666666",
                "0.5833333333333333",
                "0.5"
            ]
        },
        {
            "id": "US",
            "name": "U<sub>s</sub>",
            "description": "System utilization",
            "values": [
                "0.6666666666666666",
                "0.5833333333333333",
                "0.5"
            ]
        },
        {
            "id": "NAvg",
            "name": "<span class=\"overline\">N</span>",
            "description": "Average number of customers in the system",
            "values": [
                "1.9999999999999998",
                "1.3999999999999995",
                "1.0"
            ]
        }
    ],
    "stream": [
        4.0,
        3.5,
        3.0
    ]
}
```

### Errors

- If anything goes wrong, this is the response:
  - `400` - BAD_REQUEST: See the error message
  - `404` - NOT_FOUND: wrong endpoint
  - `405` - METHOD_NOT_ALLOWED: wrong HTTP method
  - `406` - NOT_ACCEPTABLE: HTTP body not valid JSON
  - `500` - INTERNAL_SERVER_ERROR: any other error during the request processing (except `422`)
