# Bloat-free image. Useful as a base for further customization.
DESCRIPTION = "Linux image with tarp tools and many dev packages"
LICENSE = "CLOSED"

inherit core-image

#IMAGE_FSTYPES += "ext4 ext4.gz tar squashfs squashfs-xz wic.bz2 wic.bmap"
IMAGE_FSTYPES += "tar squashfs wic.bmap"

# 65Mbits
IMAGE_ROOTFS_EXTRA_SPACE:="65536"

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

IMAGE_FEATURES += "dbg-pkgs"
IMAGE_FEATURES += "dev-pkgs"

# strace, gdb etc
IMAGE_FEATURES += "tools-debug"

# ssh
IMAGE_FEATURES += "ssh-server-openssh"
IMAGE_FEATURES += "allow-root-login"
IMAGE_FEATURES += "empty-root-password"

# List of packages we do not need that should not go into the image
PACKAGE_EXCLUDE += "acl"
PACKAGE_EXCLUDE += "cpio"
PACKAGE_EXCLUDE += "cracklib"
PACKAGE_EXCLUDE += "cronie"
PACKAGE_EXCLUDE += "cryptodev-module"
PACKAGE_EXCLUDE += "e2fsprogs"
PACKAGE_EXCLUDE += "libxml2"
PACKAGE_EXCLUDE += "m4"
PACKAGE_EXCLUDE += "packagegroup-core-full-cmdline"
PACKAGE_EXCLUDE += "wireless-tools"
PACKAGE_EXCLUDE += "grub-bootconf grub-bootmod grub-common grub-editenv grub-efi grub-efi grub-efi-env"
PACKAGE_EXCLUDE += "zephyr-demo-imx"
PACKAGE_EXCLUDE += "sof-zephyr sof-imx"
PACKAGE_EXCLUDE += "optee-client optee-test optee-os"
PACKAGE_EXCLUDE += "nxp-wlan-sdk"
PACKAGE_EXCLUDE += "packagegroup-fsl-optee-imx"
PACKAGE_EXCLUDE += "packagegroup-core-eclipse-debug"
PACKAGE_EXCLUDE += "cairo_1.16.0 cairo cairo_1 cairo_1.16 cairo-gobject cairo-script-interpreter cairo-perf-utils"
PACKAGE_EXCLUDE += "packagegroup-base-alsa packagegroup-base-bluetooth "
PACKAGE_EXCLUDE += "alsa-conf alsa-server alsa-state alsa-utils alsa-tools alsa-state"
PACKAGE_EXCLUDE += "bluez5"
PACKAGE_EXCLUDE += "gstreamer"
PACKAGE_EXCLUDE += "i2c-tools"
PACKAGE_EXCLUDE += "tzdata"
PACKAGE_EXCLUDE += "ethtool"

CORE_IMAGE_EXTRA_INSTALL:remove:append = "cl-uboot cl-deploy cl-stest cl-camera cl-audio"
CORE_IMAGE_EXTRA_INSTALL:remove:append = "libubootenv-bin"
CORE_IMAGE_EXTRA_INSTALL:remove:append = "memtester iotop tmux"
CORE_IMAGE_EXTRA_INSTALL:remove:append = "libgpiod-tools bluez5 can-utils minicom eeprom-util usbutils i2c-tools "

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

IMAGE_INSTALL += "packagegroup-base-ipv6"
IMAGE_INSTALL += "bzip2"
IMAGE_INSTALL += "fuser"
IMAGE_INSTALL += "libcap libcap-ng"
IMAGE_INSTALL += "pstree"
IMAGE_INSTALL += "psmisc"
IMAGE_INSTALL += "procps"
IMAGE_INSTALL += "strace"
IMAGE_INSTALL += "lsof"
IMAGE_INSTALL += "nftables"
IMAGE_INSTALL += "iptables"
IMAGE_INSTALL += "ipset"
IMAGE_INSTALL += "libnetfilter-conntrack"
IMAGE_INSTALL += "libnetfilter-queue"
IMAGE_INSTALL += "libnfnetlink"
IMAGE_INSTALL += "conntrack-tools"
IMAGE_INSTALL += "tcpdump"
IMAGE_INSTALL += "htop"
IMAGE_INSTALL += "iperf3"
IMAGE_INSTALL += "mtr"
IMAGE_INSTALL += "geoip"
IMAGE_INSTALL += "iftop"
IMAGE_INSTALL += "ntp"

# diff, patch, cmp etc
IMAGE_INSTALL += "diffutils"

# full-blown coreutils rather than busybox
IMAGE_INSTALL += "coreutils"

# lsfd (modern replacement for lsof), dmesg, chsh and a ton of others.
IMAGE_INSTALL += "util-linux"

# needed for nftables and such.
IMAGE_INSTALL += "python3-core"

# needed by mtrace etc
IMAGE_INSTALL += "perl"

# needed for mtr and other terminal applications.
IMAGE_INSTALL += "ncurses ncurses-terminfo-base"

# required by core tools.
IMAGE_INSTALL += "libgmpxx"

# needed by core packagegroup
IMAGE_INSTALL += "packagegroup-base-zeroconf"
# This will also pull in the following dependencies.
#PACKAGE_EXCLUDE += "avahi-daemon avahi-utils avahi-autoipd avahi-dnsconfd"
#PACKAGE_EXCLUDE += "libglib-2.0-0"
#PACKAGE_EXCLUDE += "libffi8"

IMAGE_INSTALL += "curl"
IMAGE_INSTALL += "sqlite3"
IMAGE_INSTALL += "botan"
IMAGE_INSTALL += "libmnl"
IMAGE_INSTALL += "openssl"
IMAGE_INSTALL += "vim"
IMAGE_INSTALL += "nginx"
IMAGE_INSTALL += "mosquitto"
IMAGE_INSTALL += "iproute2 iproute2-tc iproute2-ss iproute2-ip"
IMAGE_INSTALL += "wireguard-tools"
IMAGE_INSTALL += "libpcap"
IMAGE_INSTALL += "dnsmasq"
IMAGE_INSTALL += "overlayfs-tools"
IMAGE_INSTALL += "zeromq"
IMAGE_INSTALL += "lua luajit"
IMAGE_INSTALL += "protobuf"
IMAGE_INSTALL += "capnproto-compiler"
IMAGE_INSTALL += "rapidjson"
IMAGE_INSTALL += "tree"
IMAGE_INSTALL += "lxc"

# NOTE: this requires python etc
IMAGE_INSTALL += "nmap"
IMAGE_INSTALL += "netcat"

# Lua libraries
IMAGE_INSTALL += "luaposix"
IMAGE_INSTALL += "luasocket"
IMAGE_INSTALL += "luafilesystem"
IMAGE_INSTALL += "penlight"
#, luasqlite, luabitop

# tarp utils
IMAGE_INSTALL += "libtarp"
IMAGE_INSTALL += "tarp-libdsxx"
IMAGE_INSTALL += "tarp-libprocess"
IMAGE_INSTALL += "tarp-lua-semver"

# rust
