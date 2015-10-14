Welcome to the
Engineering Secure Software
Website Fuzzer v0.9

-Author(s) - Christian Greaves   -   Allen Liu and Imara Johnson

The purpose of this program is to crawl through a webpage and visit all
of the links that it finds, take note of all elements that it can find.

** - NOTE - **
This application should be working for both BodgeIt and DVWA, but I
(alone) didn't have time to test it and fix it to work with DVWA
for the custom login parameters

Features:
- Take in a custom webpage to scan.
- Follow hyperlinks on all available site pages.
- Using either a default or a custom "common words file" and a
    "common suffixes file", guess potential URLs within the domain.
- Log in to a predetermined account on the BodgeIt webapp.
- Find and print the Cookies on the pages.
- Find and print the form information found on the pages.
- Find the unique URL parameters (for example after .jsp - ?prodid=4 ).


How to use-

Extract contents of zipfile into the same directory

The script should work out of the box, but if for some reason it doesn't,
you need to install the following Python modules (included in the zip folder).

-argparse
-BeautifulSoup4
-re
-requests

Most should be part of the default python package, however.

Use-

To see all possible options for the program type
    python fuzzer.py -h
and you will get a list of all options and a description of each.

Example of use:

    python fuzzer.py --custom-auth bodgeit -discover http://localhost:8080/bodgeit/
    python fuzzer.py --custom-auth dvwa -discover http://127.0.0.1/dvwa/

What this does is set the custom authentication to the BodgeIt presets and
then discovers all links for the given url after logging in.

** - NOTE - **

The URL given must end with a forward slash ( / ) else there will be errors
in link traversal and hidden page guessing.

and

Link Traversal while logged-in may be slightly bugged - One of the links the fuzzer
follows logs the user out, so when it hits that link, we lose the custom session
and logged-in status. I believe that most of the pages that rely or utilize the
logged in status have been visited by the time this is hit, but that's beside the
point.