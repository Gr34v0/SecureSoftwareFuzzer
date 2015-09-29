#Start

import requests
from bs4 import BeautifulSoup
import argparse
import time

parser = argparse.ArgumentParser(description="Fuzzer for websites, specifically BodgeIt and DVWA")
parser.add_argument("-discover", help="Output a comprehensive, human-readable list of all discovered inputs to the system. Techniques include both crawling and guessing.")
parser.add_argument("-test", help="Discover all inputs, then attempt a list of exploit vectors on those inputs. Report potential vulnerabilities.")
parser.add_argument("--custom-auth", help="Signal that the fuzzer should use hard-coded authentication for a specific application (e.g. dvwa).")
parser.add_argument("--common-words", help="Newline-delimited file of common words to be used in page guessing and input guessing.")
parser.add_argument("--vectors-file", help="Newline-delimited file of common exploits to vulnerabilities.")
parser.add_argument("--sensitive-file", help="Newline-delimited file data that should never be leaked. It's assumed that this data is in the application's database (e.g. test data), but is not reported in any response.")
parser.add_argument("--random", help="When off, try each input to each page systematically.  When on, choose a random page, then a random input field and test all vectors. Default: false.")
parser.add_argument("--slow", help="Number of milliseconds considered when a response is considered 'slow'. Default is 500 milliseconds")

args = parser.parse_args()

visitSite = None
linkSet = []
commonWordFile = open("commonwords").read()
rootSite = None

slowTime = 500 #ms

start = time.time()

def discover(baseURL):
    url = requests.get(baseURL)
    linkSet = [baseURL]

    linkTraverse(url, linkSet, baseURL)

    for each in linkSet:
        print(str(each) + ' /n')


def linkTraverse(req, linkSet, baseURL):
    """ This crawls through the website and scrapes all unique onsite links into the the linkSet

    :param req: Website from the requests.get() command
    :param linkSet: The set of all unique links
    :param baseURL: The base URL of the site - because reasons
    :return: None
    """

    soup = BeautifulSoup(req.content)

    randomNumb = 0

    links = soup.find_all('a')

    for link in links:
        if link not in linkSet:
            linkSet.append(link)
            #print(baseURL.url + link.get('href'))
            linkTraverse(requests.get(baseURL + link.get('href')), linkSet, baseURL)


def pageGuess(someargs):
    pass

def authenticate(site):

    if site == "dvwa":
        #rootSite = requests.post("http://localhost:8080/dvwa", data={"username":"user@example.com", "password": "password"})
        #print(rootSite.content)
        #return rootSite
        pass
    elif site == "bodgeit":
        rootSite = requests.post("http://localhost:8080/bodgeit/login.jsp", data={"username":"user@example.com", "password": "password"})
        print(BeautifulSoup(rootSite.content).prettify())
        return rootSite
    else:
        print("Unrecognized or Defaulted")

if args.slow:
    slowTime = int(args.slow)

if args.common_words:
    commonWordFile = open(args.common_words).read()

if args.custom_auth and args.discover:
    if args.custom_auth == "dvwa":
        site = authenticate("dvwa")
        linkTraverse(site, linkSet, site.url)
    elif args.custom_auth == "bodgeit":
        site = authenticate("bodgeit")
        linkTraverse(site, linkSet, site.url)
    else:
        authenticate("Not Recognized, or Default")
elif args.custom_auth:
    if args.custom_auth == "dvwa":
        site = authenticate("dvwa")
    elif args.custom_auth == "bodgeit":
        site = authenticate("bodgeit")
    else:
        authenticate("Not Recognized, or Default")


if args.discover:
    discover(args.discover)

if args.test:
    pageGuess(args.test)

finalTime = time.time() - start

timeString = "This fuzzer took " + str(finalTime) + " seconds to run"

if finalTime*1000 > slowTime:
    timeString += ", and is considered slow"

print(timeString + ".")