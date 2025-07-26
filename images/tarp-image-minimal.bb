# Bloat-free image. Useful as a base for further customization.
DESCRIPTION = "Minimal Linux image with tarp tools"
LICENSE = "CLOSED"

inherit core-image

PACKAGE_CLASSES = "package_ipk"

#IMAGE_FSTYPES += "ext4 ext4.gz tar squashfs squashfs-xz wic.bz2 wic.bmap"
IMAGE_FSTYPES += "tar squashfs wic.bmap"

IMAGE_ROOTFS_EXTRA_SPACE:="1024"

INIT_MANAGER = "mdev-busybox"

#######################################################
# =============== IMAGE FEATURES ==================== #
#######################################################
# Select Image Features;

# see poky/meta/classes/core-image.bbclass for the available features;
IMAGE_FEATURES:remove := "debug-tweaks \
                          tools-profile \
                          tools-sdk \
                          package-management \
                          splash \
                          nfs-server \
                          perf \
                          hwcodecs \
                          weston \
                          x11 \
                          x11-base \
                          x11-sato \
"

# do not add dbg packages to the rootfs.
IMAGE_FEATURES:remove:append := "dbg-pkgs"

IMAGE_FEATURES += "ssh-server-dropbear"

# List of packages we do not need that should not go into the image
PACKAGE_EXCLUDE += "acl"
PACKAGE_EXCLUDE += "bzip2"
PACKAGE_EXCLUDE += "cpio"
PACKAGE_EXCLUDE += "coreutils"
PACKAGE_EXCLUDE += "cracklib"
PACKAGE_EXCLUDE += "cronie"
PACKAGE_EXCLUDE += "cryptodev-module"
PACKAGE_EXCLUDE += "diffutils"
PACKAGE_EXCLUDE += "e2fsprogs"
PACKAGE_EXCLUDE += "fuser"
PACKAGE_EXCLUDE += "libcap libcap-ng"
PACKAGE_EXCLUDE += "libfdisk1"
PACKAGE_EXCLUDE += "libffi8"
PACKAGE_EXCLUDE += "libglib-2.0-0"
PACKAGE_EXCLUDE += "libgmp10"
PACKAGE_EXCLUDE += "libncurses5"
PACKAGE_EXCLUDE += "libpopt0"
PACKAGE_EXCLUDE += "libpcre1"
PACKAGE_EXCLUDE += "libxml2"
PACKAGE_EXCLUDE += "m4"
PACKAGE_EXCLUDE += "ncurses"
PACKAGE_EXCLUDE += "packagegroup-core-full-cmdline"
PACKAGE_EXCLUDE += "pstree"
PACKAGE_EXCLUDE += "psmisc"
PACKAGE_EXCLUDE += "perl"
PACKAGE_EXCLUDE += "wireless-tools"
PACKAGE_EXCLUDE += "util-linux"
PACKAGE_EXCLUDE += "grub-bootconf grub-bootmod grub-common grub-editenv grub-efi grub-efi grub-efi-env"
PACKAGE_EXCLUDE += "zephyr-demo-imx"
PACKAGE_EXCLUDE += "sof-zephyr sof-imx"
PACKAGE_EXCLUDE += "packagegroup-base-ipv6"
PACKAGE_EXCLUDE += "optee-client optee-test optee-os"
PACKAGE_EXCLUDE += "nxp-wlan-sdk"
PACKAGE_EXCLUDE += "packagegroup-fsl-optee-imx"
#PACKAGE_EXCLUDE += "packagegroup-base"
#PACKAGE_EXCLUDE += "packagegroup-base-extended"
PACKAGE_EXCLUDE += "ncurses-terminfo-base"
PACKAGE_EXCLUDE += "packagegroup-core-eclipse-debug"
PACKAGE_EXCLUDE += "cairo_1.16.0 cairo cairo_1 cairo_1.16 cairo-gobject cairo-script-interpreter cairo-perf-utils"
PACKAGE_EXCLUDE += "packagegroup-base-alsa packagegroup-base-bluetooth packagegroup-base-zeroconf"
PACKAGE_EXCLUDE += "python3"
PACKAGE_EXCLUDE += "alsa-conf alsa-server alsa-state alsa-utils alsa-tools alsa-state"
PACKAGE_EXCLUDE += "avahi-daemon avahi-utils avahi-autoipd avahi-dnsconfd"
PACKAGE_EXCLUDE += "bluez5"
PACKAGE_EXCLUDE += "htop"
PACKAGE_EXCLUDE += "gstreamer"
PACKAGE_EXCLUDE += "i2c-tools"
PACKAGE_EXCLUDE += "python3-core"
PACKAGE_EXCLUDE += "python3-json"
PACKAGE_EXCLUDE += "python3-datetime"
PACKAGE_EXCLUDE += "tzdata"
PACKAGE_EXCLUDE += "ethtool"
PACKAGE_EXCLUDE += "sysklogd"

CORE_IMAGE_EXTRA_INSTALL:remove:append = "cl-uboot cl-deploy cl-stest cl-camera cl-audio"
CORE_IMAGE_EXTRA_INSTALL:remove:append = "libubootenv-bin"
CORE_IMAGE_EXTRA_INSTALL:remove:append = "memtester htop iotop tmux iperf3"
CORE_IMAGE_EXTRA_INSTALL:remove:append = "libgpiod-tools bluez5 can-utils minicom eeprom-util usbutils i2c-tools "
CORE_IMAGE_EXTRA_INSTALL:remove:append = "wireless-tools dhcpcd ifupdown wpa-supplicant ntp ntpdate "

#
# ------------- installs -------------------

# required for libkmod etc
IMAGE_INSTALL += "liblzma"

# required by packagegroup-base{-extended}
IMAGE_INSTALL += "usbutils"

# boot capabilities and basic cli
# see class-recipe/core-image.bbclass.
IMAGE_INSTALL += "packagegroup-core-boot"
IMAGE_INSTALL += "packagegroup-base"
IMAGE_INSTALL += "packagegroup-base-extended"

# tarp utils
IMAGE_INSTALL += "libtarp"
IMAGE_INSTALL += "tarp-libdsxx"
IMAGE_INSTALL += "tarp-libprocess"
IMAGE_INSTALL += "tarp-lua-semver"


