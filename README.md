# PodcastApp
A simple podcast application (without video ads) built on Java & Spring Boot for the bank end, hosted on AWS. Front end is currently in process.

## Functionality
* New podcasts can be added through a POST request to **IonCasts-env.eba-edscnn8c.us-east-2.elasticbeanstalk.com/ioncast/new** with the RSS-feed url in the body as a string
* A list of all currently tracked podcasts can be retrieved with a GET request to **IonCasts-env.eba-edscnn8c.us-east-2.elasticbeanstalk.com/ioncast/all**
* A list of all episodes and their respective data points can be retrieved with a GET request to **IonCasts-env.eba-edscnn8c.us-east-2.elasticbeanstalk.com/ioncast/allEpisodes**
  * The body should be a JSON message structured as follows, with details from an already added podcast:<br>
    {<br>
        "podcast_id": <numeric id here>,<br>
        "title": "<title here>",<br>
        "link": "<link here>",<br>
        "description": "<description here>",<br>
        "image": "<image link here>",<br>
        "podcastEpisodes": [],<br>
        "downloadedEpisodes": []<br>
    }

## Stack
* Backend - Java/Spring Boot
* Data Layer - MySQL
* Cloud - AWS (EBS)
* Frontend - TBD

### Project Status
After consideration of the purpose and desired outcome of the application, I have decided to not go further than a basic backend implementation of the ReST endpoints. I intend to use this application on an android phone. Having the logic in a remote location, adding latency to operation and also using up bandwidth is not an ideal implementation. Currently exploring technologies to host the logic and storage locally, such as building logic in Angular within Ionic.
