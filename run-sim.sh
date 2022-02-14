(timeout --preserve-status 10s ./gradlew simulateJava)
if [ $? -eq 143 ]; then
  echo 'Simulation SUCCESS!'
  exit 0
else
  echo 'Simulation FAILED!'
  exit 1
fi
