# About
A fitness tracker that reads data off a TCX file/s and calculates/displays relative statistics.
# Build
```sh
mvn clean package
```


The built JAR will be in `target`.
# Run
```sh
java -jar <path-to-jar>
```
# Miscellaneous
- Information about each class can be found in the respective class file.
- The program has been tested using files from [this](https://github.com/firefly-cpp/tcx-test-files)
repository. It checks for this specific TCX structure. Any deviation from that will result in no processing
of the file whatsoever.
- Everything, except for the bonus stuff (It made the code too cluttered), has been implemented.
# Reference images
* Main menu:

![Main menu screenshot](images/Screenshot%20From%202026-01-19%2005-50-00.png)
* Formula selection & daily target:


![Formula/target selection](images/Screenshot%20From%202026-01-19%2005-50-14.png) 
* Results screen:


![Results screen](images/Screenshot%20From%202026-01-19%2005-50-35.png)
* Total stats:


![Total stats](images/Screenshot%20From%202026-01-19%2005-50-44.png)
* Calorie target verdict:


![Target verdict](images/Screenshot%20From%202026-01-19%2005-50-52.png)