git clone https://github.com/<your-username>/meditrack.git
cd meditrack

javac -d out src/com/airtribe/meditrack/Main.java

java -cp out com.airtribe.meditrack.Main

java -cp out com.airtribe.meditrack.Main --loadData
