# tweet-freq

A simple Clojure program that outputs the number of tweets published to your timeline by users that you follow. (The output is the number of tweets per user, considered over a period of the last 800 tweets published to your timeline.)

Twitter's [statuses/home-timeline](https://dev.twitter.com/docs/api/1.1/get/statuses/home_timeline) resource is used to access tweet data. (Twitter limits the number of tweets that can be obtained using this resource to 800.)

## Usage

_Note: Requires [Leiningen 2](http://leiningen.org)._

Edit `make-oauth-creds` in `./src/tweet_freq/core.clj` with the the requisite OAuth details (`*app-consumer-key*`, `*app-consumer-secret*`, `*user-access-token*`, `*user-access-token-secret*`). _Note: you will need to create a new Twitter app (or use an existing app). See all of your Twitter apps (or create a new one) at https://dev.twitter.com/apps._

Install dependencies:

```bash
$ lein deps
```

Run tests:

```bash
$ lein midje
```


Run program:

```bash
$ lein run
```

The output will be a table, in the following format:

```
===========================
Username        | Frequency
===========================
jeffjarvis      | 84
mathewi         | 42
umairh          | 41
cnnbrk          | 29
Lessien         | 27
JasonHirschhorn | 24
TIME            | 18
FastCompany     | 14
... snip ...
===========================
```

## License

Copyright © 2012 Michael J Russo.

Distributed under the MIT License.
