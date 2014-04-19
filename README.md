GlassSettings
=========
## About ##
GlassSettings is an easy to use library for the Google Glass GDK that let's you build a simple preference Activity.

There are ready-made preferences for toggles and for multiple-choice items, but you can easily add your own by simply extending the `AbstractPreference` class.

Written by Victor Kaiser-Pendergrast

## How To Use ##

You can see a demo application [here](https://github.com/victorkp/GlassSettings/tree/master/Demo). All of the interesting code takes place in [MainActivity](https://github.com/victorkp/GlassSettings/blob/master/Demo/src/com/example/glasssettingsdemo/MainActivity.java)

Steps to use
  1. Create an Activity that extends `GlassPreferenceActivity`
  2. In `OnCreate`, add as many preferences as you want with `addTogglePreference`, `addChoicePreference`, `addActivityPreference`, `addHeadTiltPreference` or use `addPreference` with your own custom preference (that extends `AbstractPreference`).
  3. Call `buildAndShowOptions()` at the end of `onCreate` to show all your preferences

## Preference Types ##
GlassSettings provides three Preference types that are completely ready for use with no additional setup:
 1. TogglePreference
  - A simple Preference that can be on or off
 2. ChooserPreference
  - A Preference that gives the user a list to choose from
  - Lists of options can be easily assembled with the `OptionsBuilder` class (see the Demo source)
 3. HeadTiltPreference
  - A Preference that uses the accelerometer to let the user pick the amount of up/down tilt of Glass
  - Note that this Preference does not store an angle, but rather acceleration in meters/second<sup>2</sup> in Glass's Z axis
  - In order to use this Preference, you will have to define an Activity in AndroidManifest.xml:


        <activity
            android:enabled="true"
            android:exported="true"
            android:name="com.victor.kaiser.pendergrast.settings.types.activity.HeadTiltPreferenceActivity"
            android:label="@string/title_activity_glass_preference" >
        </activity>

In addition to those three completely packaged Preferences, there are two additional that let custom experiences be created
 1. ActivityPreference
  - Launches an Activity when tapped
  - The Preference's key is passed in as an extra with the key `"preference_key"`, and can be retrieved with `getIntent().getExtras().getString("preference_key")`
  - By extending `AbstractPreferenceActivity`, there are built in methods to get and put values into `SharedPreferences`, play click/success sounds, and get the preference key (see [here](https://github.com/victorkp/GlassSettings/blob/master/Library/src/com/victor/kaiser/pendergrast/settings/types/activity/AbstractPreferenceActivity.java))
  - `HeadTiltPreferenceActivity` extends `AbstractPreferenceActivity`, so that may be helpful to look at
 2. AbstractPreference
  - Used to create completely custom Preference functionality
  - See below for how to use

## Creating Your Own Kinds of Preferences with AbstractPreference##

There are two kinds of preference that you can make:
  1. A preference that handles everything when it is tapped (See [TogglePreference](https://github.com/victorkp/GlassSettings/blob/master/Library/src/com/victor/kaiser/pendergrast/settings/types/TogglePreference.java) and [ActivityPreference](https://github.com/victorkp/GlassSettings/blob/master/Library/src/com/victor/kaiser/pendergrast/settings/types/ActivityPreference.java))
  2. A preference that shows a series of choices when it is tapped (See [ChooserPreference](https://github.com/victorkp/GlassSettings/blob/master/Library/src/com/victor/kaiser/pendergrast/settings/types/ChooserPreference.java))

- Create a class that extends `AbstractPreference`
- Create constructors
- Override `getCard()`. This is where you will create the `View` for your preference. `getCard()` is called every time *GlassSettings* thinks that your preference's `View` might have to be updated
- Override `onSelect()`:
    - If you're building something like [TogglePreference](https://github.com/victorkp/GlassSettings/blob/master/Library/src/com/victor/kaiser/pendergrast/settings/types/TogglePreference.java), then add all your functionality here and return `true` (indicating that everything is done). You can leave `getOptions()` and `onOptionsItemSelected()` alone
    - If you're building something more like [ChooserPreference](https://github.com/victorkp/GlassSettings/blob/master/Library/src/com/victor/kaiser/pendergrast/settings/types/ChooserPreference.java), in `onSelect()` return `false` and add functionality to `getOptions()` and `onOptionsItemSelected(int)`
        - The `List` returned by `getOptions()` will be used to populate an `OptionsMenu`
        - If an item is selected in the `OptionsMenu`, then `onOptionsItemSelected(int)` will be called with the index of the selected item

That's all there is to it! 

## Contact and Future Development ##

If you have any questions, comments, or suggestions, feel free to reach me at <v.kaiser.pendergrast@gmail.com>

This library is still in a very early stage, so expect to see frequent updates with more kinds of preferences and additional functionality (perhaps the ability to define preference screens in XML).

## Screenshots ##
Toggle Preference Unchecked:

![Unchecked Toggle](http://raw2.github.com/victorkp/GlassSettings/master/Screenshots/screen1.png)

Toggle Preference Checked:

![Checked Toggle](http://raw2.github.com/victorkp/GlassSettings/master/Screenshots/screen2.png)

Choice Preference:

![Choice Preference](http://raw2.github.com/victorkp/GlassSettings/master/Screenshots/screen3.png)

Choice Preference Tapped:

![Tapped Choice Preference](http://raw2.github.com/victorkp/GlassSettings/master/Screenshots/screen4.png)

Choice Preference After Selection:

![Unchecked](http://raw2.github.com/victorkp/GlassSettings/master/Screenshots/screen5.png)
