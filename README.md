# Rating Reminder lib : encourage users to rate/comment your Android app

## Introduction

Android apps ranking on Google Playstore and others Android appstores is using ratings and comments from users. 

To get well ranked, an app needs a lot of comments and (good) ratings. The best way to achieve this is to ask 
users to put a comment on the appstore app's page.


This library facilitates this task, and can be configured to display an alert dialog periodically (each "gap" calls)
or incrementally (gap is doubled each time the dialog is shown) until user accepts to put a comment.


  * *New:* Demo app in source code illustrating lib integration, see [/app](https://github.com/openkwaky/ratingreminder/blob/master/app) directory
  * Now installable by **gradle**
  * Russian appstore **Yandex** support added. Alert dialogs have now 3 buttons, "No thanks" button added.
  * Multiple appstores support added for **Google Playstore**, **Amazon App-Shop** and **Kurio Store**. Build once, distribute on all !!
  * Easy and fast integration
  * A lot of facultative options
  * Customizable
  * Opensource : use, share for free under **Apache License 2.0** and contribute !

Follow me on twitter [@openkwaky](https://twitter.com/openkwaky)

![basic dialog](https://github.com/openkwaky/ratingreminder/blob/master/pics/mini_basic_dialog.png)
![image dialog](https://github.com/openkwaky/ratingreminder/blob/master/pics/mini_image_dialog.png)
![appstore selection](https://github.com/openkwaky/ratingreminder/blob/master/pics/mini_appstore_selection.png)

##Installation

[ ![Download](https://api.bintray.com/packages/openkwaky/maven/net.equasoft.ratingreminder/images/download.svg) ](https://bintray.com/openkwaky/maven/net.equasoft.ratingreminder/_latestVersion)

Put this line in your *build.gradle* file in the *dependency* section:

```groovy
compile 'net.equasoft.ratingreminder:library:1.+'
```

And add the *jcenter* repository if it is not yet declared:

```groovy
repositories {
    jcenter()
}
```


##Integration

###Basic integration Example

in a `FragmentActivity` (required) :

```java
RatingReminder reminder = new RatingReminder(this);
reminder.setAppName(getString(R.string.app_name));
reminder.process();
```

### Advanced integration Example

in a `FragmentActivity` (required) :

```java
RatingReminder reminder = new RatingReminder(this);
reminder.setAppName(getString(R.string.app_name));
reminder.setAlgoType(AlgoType.REGULAR_ALGO);
reminder.setDialogType(RatingDialogType.IMAGE_DIALOG);
reminder.setGap(4);

ArrayList<StoreType> storeTypes = new ArrayList<StoreType>();
storeTypes.add(StoreType.GOOGLE_PLAYSTORE);
storeTypes.add(StoreType.AMAZON_STORE);
storeTypes.add(StoreType.KURIO_STORE);
storeTypes.add(StoreType.YANDEX_STORE);
reminder.setStoreTypes(storeTypes);

reminder.process();
```

### Options details

  * Constructor `RatingReminder(FragmentActivity)` *required*
  * `setAppName(String)` : Specify the name of the app to be displayed in the dialog *required*
  * `setAlgoType(AlgoType)` : Specify a pre-defined algorithm that will decide to trigger or not the alert dialog each time RatingReminder is executed *facultative* - default `AlgoType.DOUBLE_GAP_ALGO`
  * `setDialogType(RatingDialogType)` : Specify a pre-defined dialog display *facultative* - default `BASIC_DIALOG`
  * `setGap(int)` : This method sets the field "gap" an int value that is used by the chosen algo to trigger a dialog display *facultative* - default `3`
  * `setStoreTypes(ArrayList<StoreType>)` : This method sets the appstores where the app is configured and available. The lib detects if each appstore is installed and displays only installed appstores *facultative* - default `StoreType.GOOGLE_PLAYSTORE`
  * `process()` : using algo and number of times this method has been called, it will display or not an alert dialog *required*


## They are using it !

[P3 Free Secure Applocker](https://play.google.com/store/apps/details?id=com.p3authentication)

[Radio France Podcast](https://play.google.com/store/apps/details?id=fr.radiofrance.rfpodcasts)

## Referenced by

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Rating%20Reminder-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/397)

## License

```
Copyright 2015 Openkwaky (Equasoft)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
