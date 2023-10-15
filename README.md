# APIExercise
handles HTTP requests related to alerts.

## Write Alerts

### Request

```
HTTP Method = POST
Request URI = /alerts
Headers = [Content-Type:"application/json;charset=UTF-8", Content-Length:"230"]
Body = {"alert_id":"b950482e9911ec7e41f7ca5e5d9a424f","service_id":"my_test_service_id","service_name":"my_test_service","model":"my_test_model","alert_type":"anomaly","alert_ts":"1695644160","severity":"warning","team_slack":"slack_ch"}
```
### Success Reponse
```
Status = 200
Headers = [Content-Type:"application/json"]
Content type = application/json
Body = {"alertId":"b950482e9911ec7e41f7ca5e5d9a424f","error":""}
```

### Failed Response (duplicate alert id)
```
Status = 500
Headers = [Content-Type:"application/json"]
Content type = application/json
Body = {"alertId":"b950482e9911ec7e41f7ca5e5d9a424f","error":"Internal server error"}
```

### Failed Reponse (invaild timestamp)
```
Status = 400
Headers = [Content-Type:"application/json"]
Content type = application/json
Body = {"alertId":"b950482e9911ec7e41f7ca5e5d9a424fd","error":"Alert_ts is not a valid long value."}
```

## Read Alerts

### Request
``` GET /alerts?service_id=my_test_service_id&start_ts=1695643160&end_ts=1695644360```
### Success Response

```
Status = 200
Headers = [Content-Type:"application/json"]
Content type = application/json
Body = {
    "service_id": "my_test_service_id",
    "service_name": "my_test_service",
    "alerts": [
        {
            "alert_id": "b950482e9911ec7e41f7ca5e5d9a424f",
            "model": "my_test_model",
            "alert_type": "anomaly",
            "alert_ts": "1695644160",
            "severity": "warning",
            "team_slack": "slack_ch"
        },
        {
            "alert_id": "b950482e9911ecsdfs41f75e5d9az23cv",
            "model": "my_test_model",
            "alert_type": "anomaly",
            "alert_ts": "1695644060",
            "severity": "warning",
            "team_slack": "slack_ch"
        }
    ]
}
```

### Failed Response(invailed timestamp)
```
Status = 400
Headers = [Content-Type:"application/json"]
Content type = application/json
Body = {"success":false,"alertId":"my_test_service_id","error":"End timestamp is less than start timestamp."}
```


### Failed Reponse(service id not found)
```
Status = 404
Headers = [Content-Type:"application/json"]
Content type = application/json
Body = {"success":false,"alertId":"m_test_service_id","error":"No alerts available."}
```
