GlassSettings by Victor Kaiser-Pendergrast
=========
## About ##
GlassSettings is an easy to use library for the Google Glass GDK that let's you build a simple preference Activity.

There are ready-made preferences for toggles and for multiple-choice items, but you can easily add your own by simply extending the `AbstractPreference` class.

## How To Use ##

You can see a demo application [here](https://github.com/victorkp/GlassSettings/tree/master/Demo). All of the interesting code takes place in [MainActivity](https://github.com/victorkp/GlassSettings/blob/master/Demo/src/com/example/glasssettingsdemo/MainActivity.java)

Steps to use
  1. Create an Activity
  2. In `OnCreate`, add as many preferences as you want with `addToggle`, `addChoice`, or use `addPreference` with your own custom preference (that extends `AbstractPreference`).
  3. Call `buildAndShowOptions()` at the end of `onCreate` to show all your preferences

## Creating Your Own Kinds of Preferences ##

There are two kinds of preference that you can make:
  1. A preference that handles everything when it is tapped (See [TogglePreference](https://github.com/victorkp/GlassSettings/blob/master/Library/src/com/victor/kaiser/pendergrast/settings/types/TogglePreference.java))
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

![Unchecked Toggle](https://raw2.github.com/victorkp/GlassSettings/master/Screenshots/screen1.jpg)

Toggle Preference Checked:

![Checked Toggle](https://raw2.github.com/victorkp/GlassSettings/master/Screenshots/screen2.jpg)

Choice Preference:

![Choice Preference](https://raw2.github.com/victorkp/GlassSettings/master/Screenshots/screen3.jpg)

Choice Preference Tapped:

![Tapped Choice Preference](https://raw2.github.com/victorkp/GlassSettings/master/Screenshots/screen4.jpg)

Choice Preference After Selection:

![Unchecked](https://raw2.github.com/victorkp/GlassSettings/master/Screenshots/screen5.jpg)