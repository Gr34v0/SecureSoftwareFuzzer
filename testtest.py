__author__ = 'Christian'

commonWordFile = open('commonwords').read()
print(commonWordFile)

finalTime = 5.0

timeString = "This fuzzer took " + str(finalTime) + " seconds to run"

print(timeString)