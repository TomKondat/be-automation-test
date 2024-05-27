# be-automation-test

This repo contains the first assignment. It uses Maven to manage packages.

## Setting up the environment

We use weather data from the `openweathermap` API, and so I was provided with an API key.
As we shouldn't commit sensitive data to Git, I use an environment variable to provide it to the code.

Create a `.env` file in the project's root and insert this line:

```
API_KEY=<INSERT KEY HERE>
```
