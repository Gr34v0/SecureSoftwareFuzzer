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
global linkSet
linkSet = []
global cookieBasket
cookieBasket = [] #Sam (my roommate) had this idea
global formSet
formSet = []
global guessedLinkSet
guessedLinkSet = []

global commonSuffixFile
commonSuffixFile = open("commonsuffix").read()
global commonWordFile
commonWordFile = open("commonwords").read()
#rootSite = None

global session
session = requests.Session()

slowTime = 500 #ms

start = time.time()

def discover(baseURL):
    print("------------<Input Discovery>-------------- \n")
    url = session.get(baseURL)
    linkSet = [baseURL]

    print("------------<Guessing...>------------ \n")
    pageGuess(baseURL)

    print("------------<Traversing Links...>------------ \n")
    linkTraverse(url, linkSet, baseURL)
    for link in guessedLinkSet:
        linkTraverse(url, guessedLinkSet, baseURL)

    print("------------<Links found>------------ \n")
    for each in linkSet:
        print(str(each) + ' \n')

    print("------------<Cookies found>------------\n")
    for cookie in cookieBasket:
        print(str(cookie))

    print("------------<Forms found>------------\n")
    for form in formSet:
        print(form)

    print("------------<URL Parameters found>------------ \n")

    print("------------</Input Discovery>------------")


def linkTraverse(req, linkSet, baseURL):
    """ This crawls through the website and scrapes all unique onsite links into the the linkSet

    :param req: Website from the session.get() command
    :param linkSet: The set of all unique links
    :param baseURL: The base URL of the site - because reasons
    :return: None
    """

    soup = BeautifulSoup(req.content)

    scrapeCookies(req)
    scrapeForm(soup)

    #randomNumb = 0

    links = soup.find_all('a')

    for linka in links:
        if linka not in linkSet:
            linkSet.append(linka)
            #print(baseURL.url + link.get('href'))
            linkTraverse(session.get(baseURL + linka.get('href')), linkSet, baseURL)

def scrapeCookies(url):
    cookieList = url.cookies

    for cookie in cookieList:
        if cookie not in cookieBasket:
            cookieBasket.append(cookie)

def scrapeForm(soup):

    formList = soup.find_all('input')

    for form in formList:
        if form.get("name") not in formSet:
            formSet.append(form.get("name"))


def scrapeParams(soup):
    pass


def pageGuess(baseURL):

    words = commonWordFile.split('\n')
    suffixes = commonSuffixFile.split('\n')

    for word in words:
        for suffix in suffixes:
            test = session.get(baseURL + word + "." + suffix)

        if test.status_code != 404:
            guessedLinkSet.append(test)


def authenticate(site):

    if site == "dvwa":
        #rootSite = requests.post("http://localhost:8080/dvwa", data={"username":"user@example.com", "password": "password"})
        #print(rootSite.content)
        #return rootSite
        pass
    elif site == "bodgeit":
        rootSite = session.post("http://localhost:8080/bodgeit/login.jsp", data={"username":"test@thebodgeitstore.com", "password": "password"})
        #print(BeautifulSoup(rootSite.content).prettify())
        return rootSite
    else:
        print("Unrecognized or Defaulted")


if not args.discover or not args.test:
	print("Must have either discover or test")

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

if args.discover:
    discover(args.discover)

if args.test:
    pageGuess(args.test)

finalTime = time.time() - start

timeString = "This fuzzer took " + str(finalTime) + " seconds to run"

if finalTime*1000 > slowTime:
    timeString += ", and is considered slow"

print(timeString + ".")
