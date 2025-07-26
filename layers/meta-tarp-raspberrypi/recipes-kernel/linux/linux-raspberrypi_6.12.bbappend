# if this is set, the in-tree specified defconfig will be preferred to
# the defconfig we have here. So we unset the variable to ensure the
# defconfig here is used instead.
unset KBUILD_DEFCONFIG

KERNEL_CONFIG := "rpi4b_linux_6.12.25_lite"

FILESEXTRAPATHS:prepend := "${THISDIR}/configs/${KERNEL_CONFIG}:"

# must be named 'defconfig' for yocto to automatically pick it up
# and apply it to the kernel.
SRC_URI += "file://defconfig"
