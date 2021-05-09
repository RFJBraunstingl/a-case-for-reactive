from locust import HttpUser, task, between


class QuickstartUser(HttpUser):
    wait_time = between(0.2, 0.3)

    @task(10)
    def read_random_joke(self):
        self.client.get("/api/joke")

    @task
    def write_new_joke(self):
        self.client.post("/api/joke", data="a new joke", headers={
            "Content-Type": "text/plain"
        })
