package org.cli.docker
import org.cli.Steps
import org.cli.Tool
import org.cli.registry.Registry


class DockerDevil implements DockerInterface {
    Steps link = new Steps()
    String tag = "demo"
    Registry reg
    DockerDevil(Registry reg = new Registry()){
        this.reg = reg
    }
    @Override
    def build() {
        String command= "docker build -t ${tag}:devil ."
        link.sh(command)
    }

    def login() {
        link.echo_j(reg.store)
        String pass="eyJwYXlsb2FkIjoiMWZZMEhBVTZjVkhiNm1QMTNIek01ZHE3QTgxZTBCaEJacUZkOVBGdEJ2MjlOOEpXR0MveDZsbnVGOXViQWFSNDlOWnp3MThRWGoveHhZWFZ0OW9MQlF4YWtNZHh5MVIvWEE5OUlqYWwzSU5jcUNJcjFRT0RDOCtNUUZtbi9McU9ZNXRaRE9VY082R3VQaXdZZkhjMjFOTUwvMHlFL1N5cld5cjBZYmwyVGxockxvbTE5NDRkQUZITENYTlYrWFU0eXJTZ3ZzbWwrZStiWDNETkEzS25yMWhGY3JPbFphaFRxc1VTOWpCTUl2MlFyKzBEMkdCa1VQeExVenVlRWdaMVNtYzNxOUd4Mzk5Y3NIeU1LejRGNnlQTU15Q0F2blVVTWU4OEJyNTEzNVY4K0hQczhHTnFXYkNoMFpZOTNtUXNQd0twamNTT2FKazMwL21oYWFpTzllWFlKUDZ6YnBUL0VPczhrTDRYNFZBRWlLYzRVU1UydzFIMzk2WlBjVnlLOHRKQjV4VGlJQXhXQjhtQ05Yb1o1VFFvekp4UjVLNGxjQ0JEMWNRZGxOWkRvaElJSEhUQWZ6MHRCenpWTDU4TW1OdFFGcVppNlZTUEFMSmNVVW90RWZjY1FraDV3d0tWQVkwM3VNV0FLc0lDMzhnSVRENlRvamNHMHF1UlkwYXlSRW5SSzJUaUtrdGlRYUk2M0lGazkvcTVQdnlUY3IxNHdSc016NjRQeU5FeXFRdFpkbXVDMEFTUUVyUE40RkVtbk55MnV3NkNRQmFadE5XMm1ReWI4bmkrWWcyTGZTdVU1OGM3Sk40NE5vWVNzQk8ydlY5VnpZc2xrbGdpalNjTzl2ajRjK3N4eUNlWm41U2JvMUk3MmJzZ0pUbFNkUWNzeGozNnJRQmo5MHQ3NjAxRXZROXp2YTc0OEhldElaRFZqQWp5UktuL3AyL0pLT0hmRmgrVlFFNFVNd0dWczlzQ0taZFJWZHlQSDFrRUdQRm1TbDBFVDVvYjgzLzk5OWdrVnVMSWxJUW1Eck1ZUmpNNGN4cERmZFhGUzE1anhNSklmS05aY040NEdnaXBWRlZsK2F3WmpVaFUxcnNlZHIrUGZsdUttZ3piVzA2bkk5MTc5SlcrcHR4ZTFmbWN4NWJGWDJHc2N1dTczNjBZbTFSOTdXVnpmSjc0dEwyY29CN0NjaWJaNGM4WWFTMzYyZFJxUTl5TjhtTUNiZFBGeEpuRWtXOWJ0RkhtNCtUalpBMGhHd1ZVaFQ3V1lUMHdnSXpQalI5M2Q3LzBXQjFFVEt5L3pYYjdZMEVRWmhIQk9Sb21HdzQ4NWJjbEpESTMiLCJkYXRha2V5IjoiQVFFQkFIaCtkUytCbE51ME54blh3b3diSUxzMTE1eWpkK0xOQVpoQkxac3VuT3hrM0FBQUFINHdmQVlKS29aSWh2Y05BUWNHb0c4d2JRSUJBREJvQmdrcWhraUc5dzBCQndFd0hnWUpZSVpJQVdVREJBRXVNQkVFREZ6bkwyM3Rhb3JRdXhaV3RnSUJFSUE3SG4xVkpQckkwdnlwRjFOUUtGL001RXFzMlFSTTFxelo2MTg0NE1xR2NScGVYaWx2dlpVZ3F0Zmt5RlhHWlNNWmJ4a0lVVE5hbFVIVkQxRT0iLCJ2ZXJzaW9uIjoiMiIsInR5cGUiOiJEQVRBX0tFWSIsImV4cGlyYXRpb24iOjE3MDM4OTQ5Njd9"
        String command = """docker login --username AWS --password ${reg.store} ${reg.accountid}.dkr.ecr.${reg.region}.amazonaws.com""".stripIndent().toString()
        String commandstd = """echo ${reg.store} | docker login --username AWS --password-stdin ${reg.accountid}.dkr.ecr.${reg.region}.amazonaws.com""".toString()
        link.script("docker login",commandstd)
    }

    def push() {
        String command = "docker push ${reg.accountid}.dkr.ecr.${reg.region}.amazonaws.com/${tag}:devil"
        link.sh{command}
    }


}