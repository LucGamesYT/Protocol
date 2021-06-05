package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.network.VarInts;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.InventoryContentPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryContentSerializer_v407 implements BedrockPacketSerializer<InventoryContentPacket> {
    public static final InventoryContentSerializer_v407 INSTANCE = new InventoryContentSerializer_v407();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryContentPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getContainerId());
        helper.writeArray(buffer, packet.getContents(), (buf, item) -> helper.writeNetItem(buf, item));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryContentPacket packet) {
        packet.setContainerId(VarInts.readUnsignedInt(buffer));
        helper.readArray(buffer, packet.getContents(), buf -> helper.readNetItem(buf));
    }
}