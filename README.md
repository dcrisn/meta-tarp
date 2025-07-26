# meta-tarp

yocto layer with recipes for tarp utilities and more.


## Contents

* classes

Common classes to inherit in recipes etc. For example, tarp paths,
helper for checking for required Lua version etc.

* conf/distro

distro configurations, ranging in their capability and memory footprint.

* images

Image configurations. These are built on top of distro configurations and
similarly range in the capability and memory footprint of the ensuing image.

* layers

Various platform-specific layers. For example raspberry-pi customization layer
to provide custom Linux kernel `.config`.
Only the relevant layer should be used (added to build/conf/bblayers.conf).
Similarly the MACHINE should be specified in build/conf/local.conf as
appropriate.

* recipes-kernel

Platform-independent Linux kernel customization. E.g. configuration fragments to
apply regardless of the platform/machine.

* recipes-opensrc

Open-source dependencies/packages that either do not have a recipe provided by
another well-known layer, or that are more convenient to have here.

* recipes-tarp

Recipes for public tarp packags.


